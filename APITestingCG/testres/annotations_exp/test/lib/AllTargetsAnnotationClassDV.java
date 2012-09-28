
package test.lib;

import lib.AllTargetsAnnotation;

@AllTargetsAnnotation(number = 0, text = "A")
public class AllTargetsAnnotationClassDV {

    @AllTargetsAnnotation(number = 0, text = "A")
    int annotatedField;

    @AllTargetsAnnotation(number = 0, text = "A")
     AllTargetsAnnotationClassDV() {
    }

     void localVarMethod() {
        @AllTargetsAnnotation(number = 0, text = "A")
        int localVariable;
    }

    @AllTargetsAnnotation(number = 0, text = "A")
     void annotatedMethod() {
    }

     void parameterMethod(
        @AllTargetsAnnotation(number = 0, text = "A")
        int param) {
    }

}
