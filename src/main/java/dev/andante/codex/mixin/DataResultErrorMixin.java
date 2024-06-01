package dev.andante.codex.mixin;

import com.mojang.serialization.DataResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(DataResult.Error.class)
public class DataResultErrorMixin<R> {
    @Redirect(method = "error", at = @At(value = "INVOKE", target = "Ljava/util/Optional;of(Ljava/lang/Object;)Ljava/util/Optional;"), remap = false)
    private Optional<R> onErrorOf(R value) {
        return Optional.ofNullable(value);
    }

    @Redirect(method = "flatMap(Ljava/util/function/Function;)Lcom/mojang/serialization/DataResult$Error;", at = @At(value = "INVOKE", target = "Ljava/util/Optional;of(Ljava/lang/Object;)Ljava/util/Optional;"), remap = false)
    private Optional<R> onFlatMapOf(R value) {
        return Optional.ofNullable(value);
    }

    @Redirect(method = "setPartial(Ljava/lang/Object;)Lcom/mojang/serialization/DataResult$Error;", at = @At(value = "INVOKE", target = "Ljava/util/Optional;of(Ljava/lang/Object;)Ljava/util/Optional;"), remap = false)
    private Optional<R> onSetPartialOf(R value) {
        return Optional.ofNullable(value);
    }
}
