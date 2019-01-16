import java.util.*
import java.util.stream.Collectors

class KeyboardMapping {

    fun changeLayout(src: String): String {
        return src.chars()
                .mapToObj { c ->
                    Optional.ofNullable(Map.map[String(Character.toChars(c))])
                            .orElse(String(Character.toChars(c)))
                }
                .collect(Collectors.joining())
    }
}
