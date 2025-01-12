package dev.andante.codex

import com.mojang.serialization.Codec
import net.minecraft.util.math.Vec2f
import org.joml.Vector2f
import org.joml.Vector3d
import kotlin.time.Duration

object ExtraCodecs {
    val VECTOR_3D: Codec<Vector3d> = Codec.DOUBLE.listOf().xmap(
        { list -> Vector3d(list[0], list[1], list[2]) },
        { vect -> listOf(vect.x, vect.y, vect.z) }
    )

    val VECTOR_2F: Codec<Vector2f> = Codec.FLOAT.listOf().xmap(
        { list -> Vector2f(list[0], list[1]) },
        { vect -> listOf(vect.x, vect.y) }
    )

    val VEC_2F: Codec<Vec2f> = VECTOR_2F.xmap(
        { vector -> Vec2f(vector.x, vector.y) },
        { vec -> Vector2f(vec.x, vec.y) }
    )

    val DURATION: Codec<Duration> = Codec.STRING.xmap(Duration::parse, Duration::toString)
}
