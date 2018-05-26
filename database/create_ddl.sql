CREATE TABLE 'oasis'
(
    'id' INTEGER PRIMARY KEY,
    'type' INTEGER,
    PRIMARY KEY(`id`),
    FOREIGN KEY ('type') REFERENCES 'oasis_type' ('id')

)

CREATE TABLE 'oasis_type'
(
    'id' INTEGER,
    'type' TEXT
    PRIMARY KEY(`id`)
)

CREATE TABLE 'water_fountain' 
(
    'id' INTEGER,
    'lat' NUMERIC,
    'lon' NUMERIC,
    'building' INTEGER,
    'floor' INTEGER,
    'near' TEXT,
    'count' INTEGER,
    PRIMARY KEY(`id`),
    FOREIGN KEY ('building') REFERENCES 'building' ('id')
)

CREATE TABLE 'vending_machine'
(
    'id' INTEGER,
    'lat' NUMERIC,
    'lon' NUMERIC,
    'building' INTEGER,
    'floor' INTEGER,
    'near' TEXT,
    'count' INTEGER,
    'type' INTEGER,
    PRIMARY KEY(`id`),
    FOREIGN KEY ('building') REFERENCES 'building' ('id'),
    FOREIGN KEY ('type') REFERENCES 'specific_types' ('id')
)

CREATE TABLE 'specific_types' 
(
    'id' INTEGER,
    'type' TEXT,
    PRIMARY KEY(`id`)
)

CREATE TABLE 'microwave' 
(
    'id' INTEGER,
    'lat' NUMERIC,
    'lon' NUMERIC,
    'building' INTEGER,
    'floor' INTEGER,
    'near' TEXT,
    'count' INTEGER,
    PRIMARY KEY(`id`),
    FOREIGN KEY ('building') REFERENCES 'building' ('id')
)

CREATE TABLE 'building'
(
    'id' INTEGER,
    'name' TEXT,
    PRIMARY KEY(`id`)
)