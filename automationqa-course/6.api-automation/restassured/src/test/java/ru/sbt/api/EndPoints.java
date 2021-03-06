package ru.sbt.api;

/**
 * Все поля объявленные в интерфейсе являются public static final.
 * Хорошей практикой будет вынести все эндпойты тестируемого приложения в интерфейс.
 * Таким образом, во первых уменьшается вероятность допустить ошибку при наборе адреса endpoint'а в каждом запросе вручную,
 * во вторых при изменении адреса endpoint'а, его нужно будет изменить только в одном месте.
 */
public interface EndPoints {
    String manufacture = "manufacturers/{id}";
    String manufactures = "manufacturers";
    String models = "models";
}
