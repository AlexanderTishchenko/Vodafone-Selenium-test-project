package VodafoneAutotest.Utilities;

import VodafoneAutotest.Models.Interfaces.CustomWebElementInterface;
import VodafoneAutotest.Models.Interfaces.ElementContainer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.*;
import java.util.List;

public class ExtendedFieldDecorator extends DefaultFieldDecorator {
    private ElementContainer elementContainer;

    public ExtendedFieldDecorator(ElementContainer container) {
        super(new DefaultElementLocatorFactory(container.getSearchContext()));
        elementContainer = container;
    }

    /**
     * Метод вызывается фабрикой для каждого поля в классе
     */
    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<CustomWebElementInterface> decoratableClass = decoratableClass(field);
        // если класс поля декорируемый
        if (decoratableClass != null) {
            ElementLocator locator = factory.createLocator(field);
            if (locator == null) {
                return null;
            }


            if (List.class.isAssignableFrom(field.getType())) {
                return createList(loader, locator, decoratableClass);
            }

            // элемент
            return createElement(loader, locator, decoratableClass);
        }
        return super.decorate(loader, field);
    }

    /**
     * Возвращает декорируемый класс поля,
     * либо null если класс не подходит для декоратора
     */

    private Class<CustomWebElementInterface> decoratableClass(Field field) {

        Class<?> clazz = field.getType();

        if (List.class.isAssignableFrom(clazz)) {

            // для списка обязательно должна быть задана аннотация
            if (field.getAnnotation(FindBy.class) == null &&
                    field.getAnnotation(FindBys.class) == null) {
                return null;
            }

            // Список должен быть параметризирован
            Type genericType = field.getGenericType();
            if (!(genericType instanceof ParameterizedType)) {
                return null;
            }
            // получаем класс для элементов списка
            clazz = (Class<?>) ((ParameterizedType) genericType).
                    getActualTypeArguments()[0];
        }

        if (CustomWebElementInterface.class.isAssignableFrom(clazz)) {
            return (Class<CustomWebElementInterface>) clazz;
        }
        else {
            return null;
        }
    }

    /**
     * Создание элемента.
     * Находит WebElement и передает его в кастомный класс
     */
    private CustomWebElementInterface createElement(ClassLoader loader,
                                                    ElementLocator locator, Class<CustomWebElementInterface> clazz) {
        WebElement proxy = proxyForLocator(loader, locator);
        return WrapperFactory.createInstance(clazz, proxy, elementContainer);
    }

    private List<CustomWebElementInterface> createList(ClassLoader loader, ElementLocator locator, Class<CustomWebElementInterface> clazz) {

        InvocationHandler handler = new LocatingCustomElementListHandler(locator, clazz, elementContainer);
        return (List<CustomWebElementInterface>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
    }
}
