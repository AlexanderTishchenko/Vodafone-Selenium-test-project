package autotest.models.interfaces;

import autotest.models.PageBase;
import org.openqa.selenium.SearchContext;

public interface ElementContainer extends SearchContext {
    PageBase getPage();

    SearchContext getSearchContext();
}
