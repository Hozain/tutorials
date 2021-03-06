package ru.sbtqa.tutorials.xunit.corematchers;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/**
 * Примеры использования стандартных матчеров библиотеки Hamcrest
 */
class CoreMatchersTest {

    private String efsName = "ЕФС - Единая Фронтальная Система";

    /*
     * ====================
     * ЛОГИЧЕСКИЕ МАТЧЕРЫ
     * ====================
     */

    /**
     * efsName - объект, который проверяем. allOf - это логический матчер библиотеки Hamcrest,
     * который принимает цепочку матчеров и ждет, что каждый будет выполнен успешно
     */
    @Test
    void shouldWorkHamcrestMatcherAllOf() {
        assertThat(efsName, allOf(startsWith("ЕФС"), containsString("Фронтальная")));
    }

    /**
     * anyOf - это логический матчер библиотеки Hamcrest,
     * который принимает цепочку матчеров и ждет, что один из них выполнится
     */
    @Test
    void shouldWorkHamcrestMatcherAnyOf() {
        assertThat(efsName, anyOf(startsWith("ЕФС"), containsString("Троль")));
    }

    /**
     * Использование матчера both и and для создания цепочек матчеров.
     * Так же в цепочку передаем спецальные матчеры startsWith и sameInstance. both - ожидает оба успешных условия
     */
    @Test
    void shouldWorkHamcrestMatcherBoth() {
        assertThat(efsName, both(startsWith("ЕФС")).and(instanceOf(String.class)));
    }

    /**
     * Использование логического матчера not в комбинации с startsWith.
     * Получается более читабельный вид
     */
    @Test
    void shouldWorkHamcrestMatcherNot() {
        assertThat(efsName, not(startsWith("ППРБ")));
    }

    /**
     * Использование логического матчера either в комбинациях с другими матчерами
     */
    @Test
    void shouldWorkHamcrestMatcherEither() {
        assertThat(efsName, either(not(equalTo(""))).or(is(equalToIgnoringCase(""))));
    }


    /*
     * ======================
     * СИНТАКСИЧЕСКИЕ МАТЧЕРЫ
     * ======================
     */

    /**
     * Использование матчера is, который влияет только на читабельность кода.ё
     * Hamcrest strives to make your tests as readable as possible.
     * For example, the is matcher is a wrapper that doesn't add any extra behavior to the underlying matcher.
     * <p>
     * Все три утверждения эквивалентны - см. ниже
     */
    @Test
    void shouldWorkHamcrestMatcherIs() {
        assertThat("ППРБ", equalTo("ППРБ"));
        assertThat("ППРБ", is(equalTo("ППРБ")));
        assertThat("ППРБ", is(("ППРБ")));
    }

    /**
     * Используем матчер anything(), который всегда отрабатывает успешно
     */
    @Test
    void shouldWorkHamcrestMatcherAnyThing() {
        assertThat(efsName, anything());
    }


    /* =====================================
     * МАТЧЕРЫ ПРОВЕРКИ СВОЙСТВ POJO-КЛАССОВ
     * =====================================
     *
     * Рекомендуемая библиотека, которая расширяет возможности работы с Beans:
     * github.com/sandromancuso/bean-property-matcher
     */

    /**
     * Проверяем свойство класса {@link PojoBean}
     */
    @Test
    void shouldWorkHamcrestHasProperty() {
        PojoBean pojoBean = new PojoBean("Artem", 29);
        assertThat(pojoBean, allOf(
                hasProperty("name", equalTo("Artem")),
                hasProperty("age")));
    }

    /**
     * Проверяем свойства одного объекта идентичны другому объекту
     */
    @Test
    void shouldWorkSamePropertyValuesAs() {
        PojoBean pojoBean1 = new PojoBean("Artem", 29);
        PojoBean pojoBean2 = new PojoBean("Artem", 29);
        assertThat(pojoBean1, samePropertyValuesAs(pojoBean2));
    }

    public class PojoBean {
        private String name;
        private Integer age;

        PojoBean(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }



    /* =====================================
     * МАТЧЕРЫ ДЛЯ РАБОТЫ С ЧИСЛАМИ
     * =====================================
     */

    /**
     * Матчер closeTo - используем, чтобы проверить равно ли значение "200.24" числу 202, в пределах диапозона +/- 10.
     * В пределах диапозона плюс, минус 10.
     */
    @Test
    void shouldWorkCloseTo() {
        assertThat(200.24, is(closeTo(202, 10)));
    }

    /**
     * Матчер greaterThan, проверяем, что 100 больше, чем 18
     */
    @Test
    void shouldWorkGreaterThan () {
        assertThat(100, greaterThan(18));
    }

    /**
     * Матчер greaterThanOrEqualTo проверяет, что проверяемое значение больше 18.
     * В текущем примере работаем со списком - используем матчер everyItem для обработки каждого значения списка
     */
    @Test
    void shouldWorkEveryItemInListGreaterThanOrEqualToNumber () {
        IntStream ages = IntStream.of(21, 25, 30, 18);
        assertThat(ages.boxed().collect(toList()), everyItem(greaterThanOrEqualTo(18)));
    }

}


