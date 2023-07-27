package dev.andante.codex

import com.google.gson.JsonElement
import com.mojang.datafixers.util.Pair
import com.mojang.serialization.Codec
import com.mojang.serialization.Decoder
import com.mojang.serialization.Encoder
import com.mojang.serialization.JsonOps
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
 * Encodes to a JSON format.
 */
fun <A> Encoder<A>.encodeJson(input: A): JsonElement? {
    return encodeStart(JsonOps.INSTANCE, input)
        .result()
        .orElse(null)
}

/**
 * Decodes from a JSON format.
 */
fun <A> Decoder<A>.decodeJson(input: JsonElement): A? {
    return decode(JsonOps.INSTANCE, input)
        .result()
        .map(Pair<A, JsonElement>::getFirst)
        .orElse(null)
}
