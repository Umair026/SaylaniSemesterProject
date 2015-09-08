package com.example.omii026.testing.Services;

/**
 * Created by Omii026 on 9/4/2015.
 */
public interface ServiceListener<T> {
    public void success(T object);
    public void error(ServiceError serviceError);
}
