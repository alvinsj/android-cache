## android-cache
<b>android-cache</b> is an Android Library Project to provide caching mechanism for other libraries
 
### Getting Started
Useful caching mechanism helps in keeping data offline, networking cache, etc 

```
CacheStore store = manager().cacheStore(); store.put("[type]", "[key]", "[value]");
```

### How it works?  
It uses SQLite to store information (type, key, value)

### Related projects
- [android-apirequest](http://github.com/alvinsj/android-apirequest)  
- [android-bootstrap](http://github.com/alvinsj/android-bootstrap)

