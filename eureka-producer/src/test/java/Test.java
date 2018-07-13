import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: wang_hui
 * @date: 2018/7/13 下午4:37
 * @since:
 */
public class Test {

    public static void main(String[] args) {
        List<SuggestionTopicAndAuthor> all = new ArrayList<>();

        List<SuggestionTopicAndAuthor> a = new ArrayList<>();
        List<SuggestionTopicAndAuthor> b = new ArrayList<>();
        SuggestionTopicAndAuthor s1 = new SuggestionTopicAndAuthor("s1");
        SuggestionTopicAndAuthor s2 = new SuggestionTopicAndAuthor("s2");
        SuggestionTopicAndAuthor s3 = new SuggestionTopicAndAuthor("s3");
        b.add(s1);
        b.add(s2);
        b.add(s3);

        all.addAll(a);
        all.addAll(b);
        System.out.println(Arrays.toString(all.toArray()));
    }

}

class SuggestionTopicAndAuthor {
    private String title;

    public SuggestionTopicAndAuthor(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
