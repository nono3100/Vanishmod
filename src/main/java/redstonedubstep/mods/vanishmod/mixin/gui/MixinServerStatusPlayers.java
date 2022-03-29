package redstonedubstep.mods.vanishmod.mixin.gui;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.authlib.GameProfile;

import net.minecraft.network.protocol.status.ServerStatus;
import redstonedubstep.mods.vanishmod.VanishConfig;

@Mixin(ServerStatus.Players.class)
public abstract class MixinServerStatusPlayers {
	@Shadow
	public int numPlayers;

	//update the onlinePlayerCount when setting the players, players should be already filtered by MixinServerStatusNetHandler; also makes use of an AT to un-final onlinePlayerCount
	@Inject(method = "setSample", at = @At("HEAD"))
	private void onSetSample(GameProfile[] players, CallbackInfo info) {
		if (VanishConfig.CONFIG.hidePlayersFromPlayerLists.get()) {
			this.numPlayers = players.length;
		}
	}
}