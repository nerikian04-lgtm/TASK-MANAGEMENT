package com.tracker.tracking;

import com.tracker.tracking.config.DatabaseConnection;

public class TestDB {

    public static void main(String[] args) {

        DatabaseConnection.getConnection();
    }
}