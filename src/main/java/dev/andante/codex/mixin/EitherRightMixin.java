package dev.andante.codex.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(targets = { "com.mojang.datafixers.util.Either$Right" })
public class EitherRightMixin<L, R> {
    @Redirect(method = "right", at = @At(value = "INVOKE", target = "Ljava/util/Optional;of(Ljava/lang/Object;)Ljava/util/Optional;"))
    private Optional<R> onLeftOf(R value) {
        return Optional.ofNullable(value);
    }
}
