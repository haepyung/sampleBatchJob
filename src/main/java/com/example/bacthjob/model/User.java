package com.example.bacthjob.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    String id;

    @NonNull
    String name;
}
