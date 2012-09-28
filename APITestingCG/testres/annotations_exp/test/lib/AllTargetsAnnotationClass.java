
package test.lib;

import lib.AllTargetsAnnotation;

@AllTargetsAnnotation(number = 0)
public class AllTargetsAnnotationClass {

    @AllTargetsAnnotation(number = 0)
    int annotatedField;

    @AllTargetsAnnotation(number = 0)
     AllTargetsAnnotationClass() {
    }

     void localVarMethod() {
        @AllTargetsAnnotation(number = 0)
        int localVariable;
    }

    @AllTargetsAnnotation(number = 0)
     void annotatedMethod() {
    }

     void parameterMethod(
        @AllTargetsAnnotation(number = 0)
        int param) {
    }

}
