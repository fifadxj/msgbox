package info.cangshudoudou.msgbox.utils;

import info.cangshudoudou.msgbox.vo.Category;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

public class CommonUtils {
    public static List<Category> sortCategoriesByPinyin(Collection<Category> collections) {
        final Comparator pinyiComparator = Collator.getInstance(java.util.Locale.CHINA);
        List<Category> result = new ArrayList<Category>(collections);
        Collections.sort(result, new Comparator<Category>() {

            @Override
            public int compare(Category o1, Category o2) {
                return pinyiComparator.compare(o1.getName(), o2.getName());
            }
        });
        return result;
    }
    
    public static Integer[] getRankRanges() {
        return new Integer[] {1, 2, 3, 4, 5};
    }
    
    public static String getRefererPage(HttpServletRequest request) {
        String gt = request.getParameter("goto");
        if (StringUtils.hasText(gt)) {
            return gt;
        }
        String referer = request.getHeader("Referer");
        
        return referer;
        //return _default;
        
    }
}

