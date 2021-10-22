package ru.job4j.collections.question;

import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    /**
     * Метод возвращает разницу в 2 множествах.
     * Для начала делаем из множества предыдущих значений мапу (для константного доступа к элементам).
     * Далее, в цикле по текущему множеству удаляем элементы из мапы.
     *
     * Вернувшееся значение сравниваем с элементами текущего множества по следующему алгоритму:
     * 1. Если значения из текущего множества нет в предыдущем множестве,
     *    считаем, что это новое добавленное значение (т.к. в предыдущем такого нет).
     * 2. Если значение из текущего множество нашлось в мапе предыдущих, но его значение не эквивалентно предыдущему,
     *    считаем, что значение изменилось.
     * 3. Все оставшиеся элементы мапы с предыдущим множеством, оставшиеся после удаления считаем за количество
     *    удаленных пользователей.
     *
     * @param previous
     *         Множество предыдущих значений.
     * @param current
     *         Мноэество текущих значений.
     *
     * @return Разница во множествах.
     */
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        var previousMap = previous.stream().collect(Collectors.toMap(User::getId, User::getName));
        for (User user : current) {
            var currentValue = previousMap.remove(user.getId());
            if (currentValue == null) {
                added++;
            } else if (!currentValue.equals(user.getName())) {
                changed++;
            }
        }
        return new Info(added, changed, previousMap.size());
    }

}
