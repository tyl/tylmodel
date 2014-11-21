package it.tylframework.data.mongo.common;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: marco
 * Date: 18/11/14
 * Time: 22:42
 */

@Data(staticConstructor="of")
public class Signature {
    private final String signature;

    public static Signature EmptySignature=new Signature("noSignature");
}
