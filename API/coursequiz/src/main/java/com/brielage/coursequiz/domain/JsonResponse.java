package com.brielage.coursequiz.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
public class JsonResponse {
    private final Map content;

    public JsonResponse(boolean succes) {
        this.content = new LinkedHashMap();
        this.content.put("success", succes);
    }

    public void add(String s1, String s2) {
        if (s1 != null &&
                !s1.isEmpty() &&
                s2 != null &&
                !s2.isEmpty())
            this.content.put(s1, s2);
    }

    public void add(String s, ArrayList a) {
        if (s != null &&
                !s.isEmpty() &&
                a != null &&
                a.size() > 0)
            this.content.put(s, a);
    }

    public void add(String s, LinkedHashMap m) {
        if (s != null &&
                !s.isEmpty() &&
                m != null &&
                !m.isEmpty())
            this.content.put(s, m);
    }

    public void add(String s, List l) {
        if (s != null &&
                !s.isEmpty() &&
                l != null &&
                !l.isEmpty())
            this.content.put(s, l);
    }

    public Map getContent() {
        return content;
    }

    public void add(String rol, Rol rol1) {
        this.content.put(rol, rol1);
    }
}
