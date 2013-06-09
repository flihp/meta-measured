IMAGE_INSTALL += " \
    pcr-calc \
    tboot-utils \
"
IMAGE_FEATURES += "package-management"

include core-image-tpm.inc
