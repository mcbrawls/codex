package dev.andante.codex.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(targets = { "com.mojang.serialization.DataResult$Success" })
public class DataResultSuccessMixin<R> {
    @Redirect(method = "result", at = @At(value = "INVOKE", target = "Ljava/util/Optional;of(Ljava/lang/Object;)Ljava/util/Optional;"), remap = false)
    private Optional<R> onResultOf(R value) {
        return Optional.ofNullable(value);
    }

    @Redirect(method = "resultOrPartial()Ljava/util/Optional;", at = @At(value = "INVOKE", target = "Ljava/util/Optional;of(Ljava/lang/Object;)Ljava/util/Optional;"), remap = false)
    private Optional<R> onResultOrPartialOf(R value) {
        return Optional.ofNullable(value);
    }

    @Redirect(method = "resultOrPartial(Ljava/util/function/Consumer;)Ljava/util/Optional;", at = @At(value = "INVOKE", target = "Ljava/util/Optional;of(Ljava/lang/Object;)Ljava/util/Optional;"), remap = false)
    private Optional<R> onResultOrPartialConsumerOf(R value) {
        return Optional.ofNullable(value);
    }
}
