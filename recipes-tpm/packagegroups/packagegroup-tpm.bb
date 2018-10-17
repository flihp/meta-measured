DESCRIPTION = "Support for TPM 1.2 device."
LICENSE = "MIT"
PR = "r0"

inherit packagegroup

RRECOMMENDS_packagegroup-tpm = " \
    kernel-module-tpm-tis \
    kernel-module-tpm-nsc \
    kernel-module-tpm-atmel \
    kernel-module-tpm-infineon \
    trousers \
    tpm-tools \
"
