package ru.job4j.gc.cache;

public class Emulator {

    private static final String CACHE_TYPE = "dir";

    private static final String CACHING_DIRECTORY = "src/main/resources/cache/";

    /**
     * Метод, в котром создается кэш.
     * Метод createCache добавил для того, чтобы удобнее было добавлять новые виды кэша для фабрики.
     * В первом случае, в кэш сначала загрузили содержимое файла, потом его получили.
     * Во втором случае попытались полчуить несуществующий в кэше файл и автоматом его туда добавили.
     */
    public static void main(String[] args) {
        var cache = createCache(CACHE_TYPE, CACHING_DIRECTORY);
        var namesFileName = "Names.txt";
        var addressFileName = "Address.txt";
        cache.put(namesFileName, cache.load(namesFileName));
        var names = cache.get(namesFileName);
        System.out.println(names);
        var address = cache.get(addressFileName);
        System.out.println(address);
    }

    private static AbstractCache<String, String> createCache(String key, String cachingDir) {
        AbstractCache<String, String> result = null;
        if ("dir".equals(key)) {
            result =  new DirFileCache(cachingDir);
        }
        return result;
    }

}
