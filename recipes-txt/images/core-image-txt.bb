require recipes-tpm/images/core-image-tpm.inc

DESCRIPTION = "Image with TXT stuff and the TPM utils."

IMAGE_INSTALL += " \
    packagegroup-tboot \
    packagegroup-txt-utils \
"
