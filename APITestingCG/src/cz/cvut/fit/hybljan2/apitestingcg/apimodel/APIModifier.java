/*
 * Copyright(c) Jan Hybl, FIT CTU in Prague. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Common Developmen and Distribution License (CDDL). You can obtain a copy of the CDDL at http://www.netbeans.org/cddl.html.
 */

package cz.cvut.fit.hybljan2.apitestingcg.apimodel;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Jan Hýbl
 */
public enum APIModifier {

    PUBLIC, PROTECTED, PRIVATE, ABSTRACT, FINAL, NATIVE, STRICTFP, STATIC,
    SYNCHRONIZED, TRANSIENT, VOLATILE, UNSET;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public static List<APIModifier> getInterfaceMethodModifierList() {
        List<APIModifier> result = new LinkedList<>();
        result.add(APIModifier.PUBLIC);
        result.add(APIModifier.ABSTRACT);
        return result;
    }

    /**
     * Method that translate java.lang.reflect.Modifier to our APIModifier.
     *
     * @param modifiers
     * @return
     */
    public static List<APIModifier> getModifiersSet(int modifiers) {
        List<APIModifier> result = new LinkedList<>();
        if (java.lang.reflect.Modifier.isAbstract(modifiers)) result.add(APIModifier.ABSTRACT);
        if (java.lang.reflect.Modifier.isNative(modifiers)) result.add(APIModifier.NATIVE);
        if (java.lang.reflect.Modifier.isPrivate(modifiers)) result.add(APIModifier.PRIVATE);
        if (java.lang.reflect.Modifier.isProtected(modifiers)) result.add(APIModifier.PROTECTED);
        if (java.lang.reflect.Modifier.isPublic(modifiers)) result.add(APIModifier.PUBLIC);
        if (java.lang.reflect.Modifier.isStatic(modifiers)) result.add(APIModifier.STATIC);
        if (java.lang.reflect.Modifier.isFinal(modifiers)) result.add(APIModifier.FINAL);
        if (java.lang.reflect.Modifier.isStrict(modifiers)) result.add(APIModifier.STRICTFP);
        if (java.lang.reflect.Modifier.isSynchronized(modifiers)) result.add(APIModifier.SYNCHRONIZED);
        if (java.lang.reflect.Modifier.isTransient(modifiers)) result.add(APIModifier.TRANSIENT);
        if (java.lang.reflect.Modifier.isVolatile(modifiers)) result.add(APIModifier.VOLATILE);
        return result;
    }

    /**
     * Method that translate javax.lang.model.element.Modifier to our APIModifier.
     *
     * @param flags Set of modifiers
     * @return
     */
    public static List<APIModifier> getModifiersSet(Set<javax.lang.model.element.Modifier> flags) {
        List<APIModifier> result = new LinkedList<>();
        if (flags.contains(javax.lang.model.element.Modifier.ABSTRACT)) result.add(APIModifier.ABSTRACT);
        if (flags.contains(javax.lang.model.element.Modifier.NATIVE)) result.add(APIModifier.NATIVE);
        if (flags.contains(javax.lang.model.element.Modifier.PRIVATE)) result.add(APIModifier.PRIVATE);
        if (flags.contains(javax.lang.model.element.Modifier.PROTECTED)) result.add(APIModifier.PROTECTED);
        if (flags.contains(javax.lang.model.element.Modifier.PUBLIC)) result.add(APIModifier.PUBLIC);
        if (flags.contains(javax.lang.model.element.Modifier.STATIC)) result.add(APIModifier.STATIC);
        if (flags.contains(javax.lang.model.element.Modifier.FINAL)) result.add(APIModifier.FINAL);
        if (flags.contains(javax.lang.model.element.Modifier.STRICTFP)) result.add(APIModifier.STRICTFP);
        if (flags.contains(javax.lang.model.element.Modifier.SYNCHRONIZED)) result.add(APIModifier.SYNCHRONIZED);
        if (flags.contains(javax.lang.model.element.Modifier.TRANSIENT)) result.add(APIModifier.TRANSIENT);
        if (flags.contains(javax.lang.model.element.Modifier.VOLATILE)) result.add(APIModifier.VOLATILE);
        return result;
    }

    /**
     * Method convert list of modifiers to string. Modifiers has always same order.
     */
    public static String modifiersToString(List<APIModifier> mods) {
        if (mods == null) return "";
        StringBuilder sb = new StringBuilder();
        if (mods.contains(APIModifier.PUBLIC)) sb.append("public ");
        if (mods.contains(APIModifier.PROTECTED)) sb.append("protected ");
        if (mods.contains(APIModifier.PRIVATE)) sb.append("private ");
        if (mods.contains(APIModifier.ABSTRACT)) sb.append("abstract ");
        if (mods.contains(APIModifier.STATIC)) sb.append("static ");
        if (mods.contains(APIModifier.FINAL)) sb.append("final ");
        if (mods.contains(APIModifier.NATIVE)) sb.append("native ");
        if (mods.contains(APIModifier.STRICTFP)) sb.append("strictfp ");
        if (mods.contains(APIModifier.SYNCHRONIZED)) sb.append("synchronized ");
        if (mods.contains(APIModifier.TRANSIENT)) sb.append("transient ");
        if (mods.contains(APIModifier.VOLATILE)) sb.append("volatile ");
        return sb.toString();
    }

    /**
     * Method checks, if the given item can be accessed with the given access level.
     *
     * For example, <code>ClassA</code> is declared as <code>public</code>. So it can be accessed
     * with all the access levels and this method will always return <code>true</code>. But, for class
     * <code>ClassB</code> that is declared as <code>protected</code>, method will return true only for
     * <code>protected</code> and <code>private</code> level. For <code>public</code> the method will
     * return <code>false</code>. Finally, for <code>private</code> classes, the method will return
     * <code>true</code> only for <code>private</code> minimal access modifier level.
     *
     * @param minimalAccessLevel    Java access modifier represented by APIModifier enum value.
     * @param verifiedItem          Item from an API.
     * @return                      Returns <code>true</code> if the item can be accessed with given access level,
     *                              <code>false</code> if not.
     */
    public static boolean checkAccessLevel(APIModifier minimalAccessLevel, APIItem verifiedItem) {
        if (verifiedItem.getModifiers().contains(PUBLIC)) {
            return true;
        } else if (verifiedItem.getModifiers().contains(PROTECTED)) {
            if (minimalAccessLevel == PUBLIC) {
                return false;
            } else {
                return true;
            }
        } else {
            if (minimalAccessLevel == PRIVATE) {
                return true;
            } else {
                return false;
            }
        }
    }
}
