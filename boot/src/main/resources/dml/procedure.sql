DROP TYPE TAKEIT.EMP_LIST;

DROP TYPE TAKEIT.EMP_MAP;

CREATE OR REPLACE TYPE TAKEIT.EMP_MAP AS OBJECT
(
  EMP_ID    NVARCHAR2(100),
  EMP_NM    NVARCHAR2(100),
  EMP_NO    NUMBER
);

CREATE OR REPLACE TYPE TAKEIT.EMP_LIST AS TABLE OF TAKEIT.EMP_MAP;




CREATE OR REPLACE PROCEDURE TAKEIT.EMP_DATA_INSERT (
     P_ARRAY IN EMP_LIST
   , V_TEST IN VARCHAR2
   , V_MSG OUT VARCHAR2
) IS
BEGIN

    V_MSG :=V_TEST || P_ARRAY.count;

    FOR i IN 1..P_ARRAY.count LOOP
        V_MSG := V_MSG || P_ARRAY(i).EMP_ID;
    END LOOP;


    EXCEPTION
     WHEN OTHERS THEN
       DBMS_OUTPUT.PUT_LINE(' ERROR: '||SQLERRM || P_ARRAY.count );
       V_MSG := ' ERROR: '||SQLERRM || P_ARRAY.count ;
       RAISE;

END EMP_DATA_INSERT;
/