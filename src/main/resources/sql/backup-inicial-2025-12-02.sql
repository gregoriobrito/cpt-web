--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.25
-- Dumped by pg_dump version 14.4

-- Started on 2025-12-03 00:25:15 -03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 9 (class 2615 OID 3005865)
-- Name: sc_prt; Type: SCHEMA; Schema: -; Owner: cpt
--

CREATE SCHEMA sc_prt;


ALTER SCHEMA sc_prt OWNER TO cpt;

--
-- TOC entry 10 (class 2615 OID 3005864)
-- Name: sc_rch; Type: SCHEMA; Schema: -; Owner: cpt
--

CREATE SCHEMA sc_rch;


ALTER SCHEMA sc_rch OWNER TO cpt;

--
-- TOC entry 8 (class 2615 OID 3005863)
-- Name: sc_sgr; Type: SCHEMA; Schema: -; Owner: cpt
--

CREATE SCHEMA sc_sgr;


ALTER SCHEMA sc_sgr OWNER TO cpt;

--
-- TOC entry 189 (class 1259 OID 3005911)
-- Name: sq_prt; Type: SEQUENCE; Schema: sc_prt; Owner: cpt
--

CREATE SEQUENCE sc_prt.sq_prt
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sc_prt.sq_prt OWNER TO cpt;

--
-- TOC entry 190 (class 1259 OID 3005913)
-- Name: sq_tim; Type: SEQUENCE; Schema: sc_prt; Owner: cpt
--

CREATE SEQUENCE sc_prt.sq_tim
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sc_prt.sq_tim OWNER TO cpt;

--
-- TOC entry 191 (class 1259 OID 3005915)
-- Name: sq_tim_usr; Type: SEQUENCE; Schema: sc_prt; Owner: cpt
--

CREATE SEQUENCE sc_prt.sq_tim_usr
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sc_prt.sq_tim_usr OWNER TO cpt;

SET default_tablespace = '';

--
-- TOC entry 186 (class 1259 OID 3005876)
-- Name: tbl_prt; Type: TABLE; Schema: sc_prt; Owner: cpt
--

CREATE TABLE sc_prt.tbl_prt (
    cd_prt numeric(10,0) NOT NULL,
    cd_rch numeric(10,0) NOT NULL,
    idt_prt character varying(200) NOT NULL,
    dt_prt timestamp without time zone NOT NULL,
    st_prt numeric(2,0) NOT NULL
);


ALTER TABLE sc_prt.tbl_prt OWNER TO cpt;

--
-- TOC entry 187 (class 1259 OID 3005886)
-- Name: tbl_tim; Type: TABLE; Schema: sc_prt; Owner: cpt
--

CREATE TABLE sc_prt.tbl_tim (
    cd_tim numeric(10,0) NOT NULL,
    cd_prt numeric(10,0) NOT NULL,
    idt_tim character varying(200) NOT NULL,
    pnt_tim numeric(5,0) NOT NULL,
    st_tim numeric(2,0) NOT NULL
);


ALTER TABLE sc_prt.tbl_tim OWNER TO cpt;

--
-- TOC entry 188 (class 1259 OID 3005896)
-- Name: tbl_tim_usr; Type: TABLE; Schema: sc_prt; Owner: cpt
--

CREATE TABLE sc_prt.tbl_tim_usr (
    cd_tim_usr numeric(10,0) NOT NULL,
    cd_tim numeric(10,0),
    cd_usr numeric(10,0),
    st_tim_usr numeric(2,0)
);


ALTER TABLE sc_prt.tbl_tim_usr OWNER TO cpt;

--
-- TOC entry 192 (class 1259 OID 3005917)
-- Name: sq_rch; Type: SEQUENCE; Schema: sc_rch; Owner: cpt
--

CREATE SEQUENCE sc_rch.sq_rch
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sc_rch.sq_rch OWNER TO cpt;

--
-- TOC entry 195 (class 1259 OID 3005936)
-- Name: sq_rch_usr; Type: SEQUENCE; Schema: sc_rch; Owner: cpt
--

CREATE SEQUENCE sc_rch.sq_rch_usr
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sc_rch.sq_rch_usr OWNER TO cpt;

--
-- TOC entry 185 (class 1259 OID 3005871)
-- Name: tbl_rch; Type: TABLE; Schema: sc_rch; Owner: cpt
--

CREATE TABLE sc_rch.tbl_rch (
    cd_rch numeric(10,0) NOT NULL,
    nm_rch character varying(200) NOT NULL,
    st_rch numeric(2,0) NOT NULL
);


ALTER TABLE sc_rch.tbl_rch OWNER TO cpt;

--
-- TOC entry 194 (class 1259 OID 3005921)
-- Name: tbl_rch_usr; Type: TABLE; Schema: sc_rch; Owner: cpt
--

CREATE TABLE sc_rch.tbl_rch_usr (
    cd_rch_usr numeric(10,0) NOT NULL,
    cd_rch numeric(10,0) NOT NULL,
    cd_usr numeric(10,0) NOT NULL,
    st_rch_usr numeric(10,0) NOT NULL,
    fg_adm_rch_usr character varying(1) NOT NULL
);


ALTER TABLE sc_rch.tbl_rch_usr OWNER TO cpt;

--
-- TOC entry 193 (class 1259 OID 3005919)
-- Name: sq_usr; Type: SEQUENCE; Schema: sc_sgr; Owner: cpt
--

CREATE SEQUENCE sc_sgr.sq_usr
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sc_sgr.sq_usr OWNER TO cpt;

--
-- TOC entry 184 (class 1259 OID 3005866)
-- Name: tbl_usr; Type: TABLE; Schema: sc_sgr; Owner: cpt
--

CREATE TABLE sc_sgr.tbl_usr (
    cd_usr numeric(10,0) NOT NULL,
    nm_usr character varying(200) NOT NULL,
    apl_usr character varying(100) NOT NULL,
    st_usr numeric(2,0) NOT NULL,
    lgn_usr character varying(50) NOT NULL,
    snh_usr character varying(500) NOT NULL
);


ALTER TABLE sc_sgr.tbl_usr OWNER TO cpt;

--
-- TOC entry 2153 (class 0 OID 3005876)
-- Dependencies: 186
-- Data for Name: tbl_prt; Type: TABLE DATA; Schema: sc_prt; Owner: cpt
--

COPY sc_prt.tbl_prt (cd_prt, cd_rch, idt_prt, dt_prt, st_prt) FROM stdin;
11	1	01 - 03/12/2025	2025-12-03 00:21:21.846	1
\.


--
-- TOC entry 2154 (class 0 OID 3005886)
-- Dependencies: 187
-- Data for Name: tbl_tim; Type: TABLE DATA; Schema: sc_prt; Owner: cpt
--

COPY sc_prt.tbl_tim (cd_tim, cd_prt, idt_tim, pnt_tim, st_tim) FROM stdin;
16	11	GG/WAN	0	1
17	11	JES/GUS	0	1
\.


--
-- TOC entry 2155 (class 0 OID 3005896)
-- Dependencies: 188
-- Data for Name: tbl_tim_usr; Type: TABLE DATA; Schema: sc_prt; Owner: cpt
--

COPY sc_prt.tbl_tim_usr (cd_tim_usr, cd_tim, cd_usr, st_tim_usr) FROM stdin;
26	16	1	1
27	16	2	1
28	17	3	1
29	17	4	1
\.


--
-- TOC entry 2152 (class 0 OID 3005871)
-- Dependencies: 185
-- Data for Name: tbl_rch; Type: TABLE DATA; Schema: sc_rch; Owner: cpt
--

COPY sc_rch.tbl_rch (cd_rch, nm_rch, st_rch) FROM stdin;
1	CAPIVARA CARRAPATO	1
\.


--
-- TOC entry 2161 (class 0 OID 3005921)
-- Dependencies: 194
-- Data for Name: tbl_rch_usr; Type: TABLE DATA; Schema: sc_rch; Owner: cpt
--

COPY sc_rch.tbl_rch_usr (cd_rch_usr, cd_rch, cd_usr, st_rch_usr, fg_adm_rch_usr) FROM stdin;
1	1	1	1	S
2	1	2	1	S
3	1	3	1	S
4	1	4	1	S
5	1	5	1	S
\.


--
-- TOC entry 2151 (class 0 OID 3005866)
-- Dependencies: 184
-- Data for Name: tbl_usr; Type: TABLE DATA; Schema: sc_sgr; Owner: cpt
--

COPY sc_sgr.tbl_usr (cd_usr, nm_usr, apl_usr, st_usr, lgn_usr, snh_usr) FROM stdin;
1	GREGORIO BRITO	GG	1	GREG	$2a$10$pURiS5ywt31nnkHih2JpHOebrYkwmc64VJiZAEzC3aYDzOk0lUNUy
2	WANESSA DUTRA	WAN	1	WANESSA	$2a$10$bmcOMjVBxSjTZ8TyzATxkeLRhVUBZd.asI3bqvo2M0VP42Gp/wZoO
3	JESSICA OLIVEIRA	JES	1	JESSICA	$2a$10$bmcOMjVBxSjTZ8TyzATxkeLRhVUBZd.asI3bqvo2M0VP42Gp/wZoO
4	GUSTAVO FACO	GUS	1	GUSTAVO	$2a$10$bmcOMjVBxSjTZ8TyzATxkeLRhVUBZd.asI3bqvo2M0VP42Gp/wZoO
5	CARLOS HUAN	HUAN	1	HUAN	$2a$10$bmcOMjVBxSjTZ8TyzATxkeLRhVUBZd.asI3bqvo2M0VP42Gp/wZoO
\.


--
-- TOC entry 2169 (class 0 OID 0)
-- Dependencies: 189
-- Name: sq_prt; Type: SEQUENCE SET; Schema: sc_prt; Owner: cpt
--

SELECT pg_catalog.setval('sc_prt.sq_prt', 11, true);


--
-- TOC entry 2170 (class 0 OID 0)
-- Dependencies: 190
-- Name: sq_tim; Type: SEQUENCE SET; Schema: sc_prt; Owner: cpt
--

SELECT pg_catalog.setval('sc_prt.sq_tim', 17, true);


--
-- TOC entry 2171 (class 0 OID 0)
-- Dependencies: 191
-- Name: sq_tim_usr; Type: SEQUENCE SET; Schema: sc_prt; Owner: cpt
--

SELECT pg_catalog.setval('sc_prt.sq_tim_usr', 29, true);


--
-- TOC entry 2172 (class 0 OID 0)
-- Dependencies: 192
-- Name: sq_rch; Type: SEQUENCE SET; Schema: sc_rch; Owner: cpt
--

SELECT pg_catalog.setval('sc_rch.sq_rch', 1, true);


--
-- TOC entry 2173 (class 0 OID 0)
-- Dependencies: 195
-- Name: sq_rch_usr; Type: SEQUENCE SET; Schema: sc_rch; Owner: cpt
--

SELECT pg_catalog.setval('sc_rch.sq_rch_usr', 5, true);


--
-- TOC entry 2174 (class 0 OID 0)
-- Dependencies: 193
-- Name: sq_usr; Type: SEQUENCE SET; Schema: sc_sgr; Owner: cpt
--

SELECT pg_catalog.setval('sc_sgr.sq_usr', 5, true);


--
-- TOC entry 2024 (class 2606 OID 3005880)
-- Name: tbl_prt pk_prt; Type: CONSTRAINT; Schema: sc_prt; Owner: cpt
--

ALTER TABLE ONLY sc_prt.tbl_prt
    ADD CONSTRAINT pk_prt PRIMARY KEY (cd_prt);


--
-- TOC entry 2026 (class 2606 OID 3005890)
-- Name: tbl_tim pk_tim; Type: CONSTRAINT; Schema: sc_prt; Owner: cpt
--

ALTER TABLE ONLY sc_prt.tbl_tim
    ADD CONSTRAINT pk_tim PRIMARY KEY (cd_tim);


--
-- TOC entry 2028 (class 2606 OID 3005900)
-- Name: tbl_tim_usr pk_tim_usr; Type: CONSTRAINT; Schema: sc_prt; Owner: cpt
--

ALTER TABLE ONLY sc_prt.tbl_tim_usr
    ADD CONSTRAINT pk_tim_usr PRIMARY KEY (cd_tim_usr);


--
-- TOC entry 2022 (class 2606 OID 3005875)
-- Name: tbl_rch pk_rch; Type: CONSTRAINT; Schema: sc_rch; Owner: cpt
--

ALTER TABLE ONLY sc_rch.tbl_rch
    ADD CONSTRAINT pk_rch PRIMARY KEY (cd_rch);


--
-- TOC entry 2030 (class 2606 OID 3005925)
-- Name: tbl_rch_usr pk_rch_usr; Type: CONSTRAINT; Schema: sc_rch; Owner: cpt
--

ALTER TABLE ONLY sc_rch.tbl_rch_usr
    ADD CONSTRAINT pk_rch_usr PRIMARY KEY (cd_rch_usr);


--
-- TOC entry 2020 (class 2606 OID 3005870)
-- Name: tbl_usr pk_usr; Type: CONSTRAINT; Schema: sc_sgr; Owner: cpt
--

ALTER TABLE ONLY sc_sgr.tbl_usr
    ADD CONSTRAINT pk_usr PRIMARY KEY (cd_usr);


--
-- TOC entry 2031 (class 2606 OID 3005881)
-- Name: tbl_prt fk_prt_rch; Type: FK CONSTRAINT; Schema: sc_prt; Owner: cpt
--

ALTER TABLE ONLY sc_prt.tbl_prt
    ADD CONSTRAINT fk_prt_rch FOREIGN KEY (cd_rch) REFERENCES sc_rch.tbl_rch(cd_rch);


--
-- TOC entry 2032 (class 2606 OID 3005891)
-- Name: tbl_tim fk_tim_prt; Type: FK CONSTRAINT; Schema: sc_prt; Owner: cpt
--

ALTER TABLE ONLY sc_prt.tbl_tim
    ADD CONSTRAINT fk_tim_prt FOREIGN KEY (cd_prt) REFERENCES sc_prt.tbl_prt(cd_prt);


--
-- TOC entry 2033 (class 2606 OID 3005901)
-- Name: tbl_tim_usr fk_tim_usr_tim; Type: FK CONSTRAINT; Schema: sc_prt; Owner: cpt
--

ALTER TABLE ONLY sc_prt.tbl_tim_usr
    ADD CONSTRAINT fk_tim_usr_tim FOREIGN KEY (cd_tim) REFERENCES sc_prt.tbl_tim(cd_tim);


--
-- TOC entry 2034 (class 2606 OID 3005906)
-- Name: tbl_tim_usr fk_tim_usr_usr; Type: FK CONSTRAINT; Schema: sc_prt; Owner: cpt
--

ALTER TABLE ONLY sc_prt.tbl_tim_usr
    ADD CONSTRAINT fk_tim_usr_usr FOREIGN KEY (cd_usr) REFERENCES sc_sgr.tbl_usr(cd_usr);


--
-- TOC entry 2035 (class 2606 OID 3005926)
-- Name: tbl_rch_usr fk_rch_usr_rch; Type: FK CONSTRAINT; Schema: sc_rch; Owner: cpt
--

ALTER TABLE ONLY sc_rch.tbl_rch_usr
    ADD CONSTRAINT fk_rch_usr_rch FOREIGN KEY (cd_rch) REFERENCES sc_rch.tbl_rch(cd_rch);


--
-- TOC entry 2036 (class 2606 OID 3005931)
-- Name: tbl_rch_usr fk_rch_usr_usr; Type: FK CONSTRAINT; Schema: sc_rch; Owner: cpt
--

ALTER TABLE ONLY sc_rch.tbl_rch_usr
    ADD CONSTRAINT fk_rch_usr_usr FOREIGN KEY (cd_usr) REFERENCES sc_sgr.tbl_usr(cd_usr);


--
-- TOC entry 2168 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2025-12-03 00:25:33 -03

--
-- PostgreSQL database dump complete
--

