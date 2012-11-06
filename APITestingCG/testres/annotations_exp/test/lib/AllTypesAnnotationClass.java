
package test.lib;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import lib.AllTypesAnnotation;
import lib.TestEnum;

public class AllTypesAnnotationClass {

    @AllTypesAnnotation(boa = {
        false
    }, bo = false, bya = {
        0
    }, by = 0, cha = {
        'a'
    }, ch = 'a', cla = {
        File.class
    }, clfa = {
        File.class
    }, clf = List.class, cl = File.class, cll = List.class, clq = Object.class, cls = Serializable.class, da = {
        0.0D
    }, d = 0.0D, ena = {
        TestEnum.A
    }, en = TestEnum.A, fa = {
        0.0D
    }, f = 0.0D, ia = {
        0
    }, i = 0, la = {
        0
    }, l = 0, sha = {
        0
    }, sh = 0, sta = {
        "A"
    }, st = "A")
    int annotatedField;

}
