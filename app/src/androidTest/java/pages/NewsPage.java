package pages;

import ru.iteco.fmhandroid.R;
import utils.EspressoBaseTest;

public class NewsPage extends EspressoBaseTest {
    public void checkEdit() {
        checkById(R.id.edit_news_material_button);
        clickButton(R.id.edit_news_material_button);
        checkById(R.id.add_news_image_view);
    }
    public void createNews() {
        String date = getCurrentDate();
        String time = getCurrentTime();
        String category = "Объявление";
        //String title = "test";
        String description = "test";
        clickButton(R.id.add_news_image_view);
        inputCategory(category);
        inputTitle(title);
        inputDate(date);
        inputTime(time);
        inputDescription(description);
        clickSaveButton();
        recyclerViewNews(title);
    }
}
