# sofastack-mesh-demo

# start without mesh (with sofa registry)

-DSOFA_INSTANCE_ID=000001
-DSOFA_ANTVIP_ENDPOINT=11.239.139.142
-DSOFA_ACCESS_KEY=
-DSOFA_SECRET_KEY=
-Dspring.profiles.active=dev

# start with mesh (with sofa registry)

-DMOSN_ENABLE=true
-DSOFA_INSTANCE_ID=000001
-DSOFA_ANTVIP_ENDPOINT=11.239.139.142
-DSOFA_ACCESS_KEY=
-DSOFA_SECRET_KEY=
-Dspring.profiles.active=dev