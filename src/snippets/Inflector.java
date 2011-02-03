package snippets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.toLowerCase;

/**
 * User: zahir
 * Date: 11-2-2
 * Time: 上午6:02
 */
public class Inflector {

    private static final Pattern UNDERSCORE_PATTERN_1 = Pattern.compile("([A-Z]+)([A-Z][a-z])");
    private static final Pattern UNDERSCORE_PATTERN_2 = Pattern.compile("([a-z\\d])([A-Z])");

    private static final Pattern CAPITALIZE_PATTERN_1 = Pattern.compile("([-_\\s])([a-z])");

    private static class Formula {
        String pattern;
        String replacement;

        private Formula(String pattern, String replacement) {
            this.pattern = pattern;
            this.replacement = replacement;
        }
    }

    private static List<Formula> pluralFormulas = new ArrayList<Formula>();
    private static List<Formula> singularFormulas = new ArrayList<Formula>();
    private static List<String> uncountableWords = new ArrayList<String>();

    public static void addPluralFormula(String pattern, String replacement) {
        pluralFormulas.add(new Formula(pattern, replacement));
    }

    public static void addSingularFormula(String pattern, String replacement) {
        singularFormulas.add(new Formula(pattern, replacement));
    }

    public static void addUncountableWords(String... words) {
        uncountableWords.addAll(Arrays.asList(words));
    }

    public static void addIrregularFormula(String singular, String plural) {
        addPluralFormula(singular, plural);
        addSingularFormula(plural, singular);
    }

    static {
        {
            addPluralFormula("$", "s");
            addPluralFormula("s$", "s");
            addPluralFormula("(ax|test)is$", "$1es");
            addPluralFormula("(octop|vir)us$", "$1i");
            addPluralFormula("(alias|status)$", "$1es");
            addPluralFormula("(bu)s$", "$1es");
            addPluralFormula("(buffal|tomat)o$", "$1oes");
            addPluralFormula("([ti])um$", "$1a");
            addPluralFormula("sis$", "ses");
            addPluralFormula("(?:([^f])fe|([lr])f)$", "$1$2ves");
            addPluralFormula("(hive)$", "$1s");
            addPluralFormula("([^aeiouy]|qu)y$", "$1ies");
            addPluralFormula("([^aeiouy]|qu)ies$", "$1y");
            addPluralFormula("(x|ch|ss|sh)$", "$1es");
            addPluralFormula("(matr|vert|ind)ix|ex$", "$1ices");
            addPluralFormula("([m|l])ouse$", "$1ice");
            addPluralFormula("(ox)$", "$1en");
            addPluralFormula("(quiz)$", "$1zes");
        }

        {
            addSingularFormula("s$", "");
            addSingularFormula("(n)ews$", "$1ews");
            addSingularFormula("([ti])a$", "$1um");
            addSingularFormula("((a)naly|(b)a|(d)iagno|(p)arenthe|(p)rogno|(s)ynop|(t)he)ses$", "$1$2sis");
            addSingularFormula("(^analy)ses$", "$1sis");
            addSingularFormula("([^f])ves$", "$1fe");
            addSingularFormula("(hive)s$", "$1");
            addSingularFormula("(tive)s$", "$1");
            addSingularFormula("([lr])ves$", "$1f");
            addSingularFormula("([^aeiouy]|qu)ies$", "$1y");
            addSingularFormula("(s)eries$", "$1eries");
            addSingularFormula("(m)ovies$", "$1ovie");
            addSingularFormula("(x|ch|ss|sh)es$", "$1");
            addSingularFormula("([m|l])ice$", "$1ouse");
            addSingularFormula("(bus)es$", "$1");
            addSingularFormula("(o)es$", "$1");
            addSingularFormula("(shoe)s$", "$1");
            addSingularFormula("(cris|ax|test)es$", "$1is");
            addSingularFormula("([octop|vir])i$", "$1us");
            addSingularFormula("(alias|status)es$", "$1");
            addSingularFormula("^(ox)en", "$1");
            addSingularFormula("(vert|ind)ices$", "$1ex");
            addSingularFormula("(matr)ices$", "$1ix");
            addSingularFormula("(quiz)zes$", "$1");
        }

        {
            addIrregularFormula("person", "people");
            addIrregularFormula("man", "men");
            addIrregularFormula("child", "children");
            addIrregularFormula("sex", "sexes");
            addIrregularFormula("move", "moves");
        }
        addUncountableWords("equipment", "information", "rice", "money", "species", "series", "fish", "sheep");
    }


    /**
     *
     * @param str
     * @return
     */
    public static String capitalize(String str) {
        return changeFirstCharacterCase(str, true);
    }

    public static String uncapitalize(String str) {
        return changeFirstCharacterCase(str, false);
    }

    public static String camelize(String str, String separatorPattern, boolean firstLetterInUppercase) {
        if (str == null) {
            return null;
        }
        if (firstLetterInUppercase) {
            str = capitalize(str);
        }

        final StringBuilder buffer = new StringBuilder(str);
        for (int i = 0; i < buffer.length() - 1; i++) {
            if ( String.valueOf(buffer.charAt(i)).matches(separatorPattern) ) {
                buffer.deleteCharAt(i);
                buffer.setCharAt(i, Character.toUpperCase(buffer.charAt(i)));
            }
        }
        return buffer.toString();
    }

    public static String camelize(String str, boolean firstLetterInUppercase) {
        return camelize(str, "_", firstLetterInUppercase);
    }

    public static String underscore(String camelCasedWord) {
        StringBuilder result = new StringBuilder();
        char[] chars = camelCasedWord.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ( i != 0 && isLowerCase(chars[i - 1]) && Character.isUpperCase(c) ) {
                result.append("_");
            }
            result.append(Character.toLowerCase(c));
        }
        return result.toString();
    }


    private static String changeFirstCharacterCase(String str, boolean capitalize) {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length());
        if (capitalize) {
            sb.append(Character.toUpperCase(str.charAt(0)));
        } else {
            sb.append(toLowerCase(str.charAt(0)));
        }
        sb.append(str.substring(1));
        return sb.toString();
    }

    public static String pluralize(String word) {
        if (uncountableWords.contains(word)) {
            return word;
        }
        return replaceWithFirstMatchFormula(word, pluralFormulas);
    }

    public static String singularize(String word) {
        if (uncountableWords.contains(word)) {
            return word;
        }
        return replaceWithFirstMatchFormula(word, singularFormulas);
    }

    private static String replaceWithFirstMatchFormula(String word, List<Formula> formulas) {

        for (Formula formula : formulas) {
            Matcher matcher = Pattern.compile(formula.pattern, Pattern.CASE_INSENSITIVE).matcher(word);
            if (matcher.find()) {
                return matcher.replaceAll(formula.replacement);
            }
        }
        return word;
    }

}
