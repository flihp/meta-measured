DESCRIPTION = "A live image init script with support for the TPM"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SRC_URI = "file://init-boot-tpm.sh"

PR = "r0"

do_install() {
        install -m 0755 ${WORKDIR}/init-boot-tpm.sh ${D}/init
}

inherit allarch

FILES_${PN} += " /init "
