LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_STATIC_JAVA_LIBRARIES += picasso
LOCAL_STATIC_JAVA_LIBRARIES += jodatime

LOCAL_MODULE := KatsunaCommon
LOCAL_CERTIFICATE := platform

LOCAL_SRC_FILES := $(call all-java-files-under, commons)

include $(BUILD_STATIC_JAVA_LIBRARY)

include $(CLEAR_VARS)

LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES := picasso:commons/libs/picasso-2.5.2.jar
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES += jodatime:commons/libs/jodatime-2.9.2.jar

include $(BUILD_MULTI_PREBUILT)
