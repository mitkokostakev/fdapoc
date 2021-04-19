DROP SCHEMA IF EXISTS fda_demo CASCADE;
CREATE SCHEMA fda_demo;

DROP TABLE IF EXISTS fda_demo.drug_record_application;
CREATE TABLE fda_demo.drug_record_application (
	application_number varchar(40) NULL,
	manufacturer_name varchar(100) NULL,
	substance_name varchar(100) NULL,
	product_numbers varchar(1024) NULL,

	deleted bool NOT NULL DEFAULT false,
	CONSTRAINT conversion_rate_pkey PRIMARY KEY (application_number),
	CONSTRAINT uk_s5g4x5w0vg828rj8r99kwvll8 UNIQUE (application_number, manufacturer_name, substance_name)
);