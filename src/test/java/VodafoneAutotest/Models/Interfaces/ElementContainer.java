package VodafoneAutotest.Models.Interfaces;

import VodafoneAutotest.Models.PageBase;
import org.openqa.selenium.SearchContext;

public interface ElementContainer extends SearchContext {
    PageBase getPage();

    SearchContext getSearchContext();
}
