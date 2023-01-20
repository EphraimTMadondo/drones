-- noinspection SqlDialectInspectionForFile
-- noinspection SqlNoDataSourceInspectionForFile

INSERT INTO musala_drones(serial_number, drone_model, maximum_weight, battery_capacity, drone_state, loaded_weight) VALUES
	('2P3bP7Xr', 'Lightweight', 200, 100, 'IDLE', 0),
    ('7SfJ5Wx4', 'Lightweight', 200, 100, 'LOADING', 50),
    ('2aTJNVin', 'Middleweight', 400, 80, 'IDLE', 0),
    ('8swrEBa7', 'Middleweight', 400, 80, 'LOADED', 350),
    ('2n2Gcso9', 'Cruiserweight', 600, 60, 'IDLE', 0),
    ('A5Zz7P2n', 'Cruiserweight', 600, 60, 'LOADING', 100),
    ('Ae2b9GVp', 'Cruiserweight', 600, 60, 'LOADED', 500),
    ('BFpNs7pJ', 'Cruiserweight', 600, 60, 'DELIVERING', 500),
    ('2p4SL3xY', 'Heavyweight', 800, 40, 'DELIVERING', 700),
    ('5LMnV6Hi', 'Heavyweight', 800, 30, 'DELIVERED', 0);