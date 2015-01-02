/*
 * Copyright 2015 Tyl Consulting s.a.s.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tylproject.data.mongo.exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mp on 31/12/14.
 */
public class TylModelException extends Exception{

    private TylModelErrorCode errorCode;
    private Map<String,Object> attributes=new HashMap<String,Object>();


    public TylModelException(String message, TylModelErrorCode errorCode){
        super(message);
        this.errorCode=errorCode;
    }

    public TylModelException(TylModelErrorCode errorCode){
        this.errorCode=errorCode;
    }

    public TylModelErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(TylModelErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    public TylModelException set(String key,Object value){
        attributes.put(key,value);
        return (this);
    }

    public Object get(String key){
        return attributes.get(key);
    }
}
