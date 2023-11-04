package dev.andante.codex

import com.mojang.datafixers.util.Pair
import com.mojang.serialization.Codec
import com.mojang.serialization.Decoder
import com.mojang.serialization.DynamicOps
import com.mojang.serialization.Encoder
import com.mojang.serialization.MapCodec
import java.util.Optional

/**
 * Converts a codec to a nullable codec, using of optional codecs as an intermediary.
 */
fun <A : Any> Codec<A>.nullableFieldOf(name: String): MapCodec<A?> {
    return optionalFieldOf(name).xmap(
        { it.orElse(null) },
        { Optional.ofNullable(it) }
    ).orElse(null)
}

/**
 * Converts a codec of a type to a codec of the same type but functional.
 */
fun <A : Any?> MapCodec<A>.functionally(): MapCodec<() -> A> {
    return xmap({ { it } }, { it() })
}

/**
 * Encodes to a dynamic ops format.
 */
fun <A, T> Encoder<A>.encodeQuick(ops: DynamicOps<T>, input: A): T? {
    return encodeStart(ops, input)
        .result()
        .orElse(null)
}

/**
 * Decodes from a dynamic ops format.
 */
fun <A, T> Decoder<A>.decodeQuick(ops: DynamicOps<T>, input: T): A? {
    return decode(ops, input)
        .result()
        .map(Pair<A, T>::getFirst)
        .orElse(null)
}
