package dev.andante.codex.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(targets = { "com.mojang.datafixers.util.Either$Left" })
public class EitherLeftMixin<L, R> {
    @Redirect(method = "left", at = @At(value = "INVOKE", target = "Ljava/util/Optional;of(Ljava/lang/Object;)Ljava/util/Optional;"), remap = false)
    private Optional<L> onLeftOf(L value) {
        return Optional.ofNullable(value);
    }
}
