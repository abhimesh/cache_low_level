// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        storage storage=new storage(5);
        evictionPolicy evictionPolicy=new evictionPolicy();
        cache cache=new cache(evictionPolicy, storage);
        cache.set("a","b");
        cache.set("c","d");
        cache.set("e","f");
        cache.set("g","h");
        cache.set("i","j");
        System.out.println(cache.get("i"));
        cache.set("k","l");
        System.out.println(cache.get("a"));
        System.out.println(cache.get("e"));
        cache.set("x","y");
        System.out.println(cache.get("c"));
        }
    }
