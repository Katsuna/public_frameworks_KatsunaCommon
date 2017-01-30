LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

# Add here only the libs (.jar) that the KatsunaCommon itself needs, to be built
LOCAL_STATIC_JAVA_LIBRARIES := jodatime
LOCAL_STATIC_JAVA_LIBRARIES += android-support-v4
LOCAL_STATIC_JAVA_LIBRARIES += android-support-v7-appcompat
LOCAL_STATIC_JAVA_LIBRARIES += android-support-v7-recyclerview
LOCAL_STATIC_JAVA_LIBRARIES += android-support-design

LOCAL_MODULE := KatsunaCommon
LOCAL_CERTIFICATE := platform

LOCAL_SRC_FILES := $(call all-java-files-under, commons)

include $(BUILD_STATIC_JAVA_LIBRARY)

include $(CLEAR_VARS)

LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES := jodatime:aosp/libs/jodatime-2.9.2.jar

# This is a definition!
# Add here all the libs (jar, aar) that are needed in all Katsuna Apps but not necessarily in KatsunaCommon
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES += picasso:aosp/libs/picasso-2.5.2.jar
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES += roundedimageview:aosp/libs/roundedimageview-2.2.1.aar
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES += fabtransformation:aosp/libs/fabtransformation-1.0.0.aar

include $(BUILD_MULTI_PREBUILT)
