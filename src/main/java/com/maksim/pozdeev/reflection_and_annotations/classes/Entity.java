package com.maksim.pozdeev.reflection_and_annotations.classes;

import com.maksim.pozdeev.reflection_and_annotations.annotations.Secured;

public class Entity {

    public boolean method1() {
        System.out.println("method1 has no annotation @Secured");
        return true;
    }

    @Secured(id = 2)
    private boolean method2() {
        System.out.println("method2 has annotation @Secured and one mandatory parameter");
        return false;
    }


    private boolean method3() {
        System.out.println("method3 has no annotation @Secured");
        return false;
    }

    @Secured(id = 4, style = "soft")
    public boolean method4() {
        System.out.println("method4 has annotation @Secured and both parameters: mandatory and optional");
        return true;
    }

    public boolean method5() {
        System.out.println("method5 has no annotation @Secured");
        return false;
    }
}
