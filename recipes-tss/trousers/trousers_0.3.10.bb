SUMMARY = "TrouSerS - An open-source TCG Software Stack implementation, created and released by IBM."
DESCRIPTION = " \
  Trousers is an open-source TCG Software Stack (TSS), released under the \
  Common Public License. Trousers aims to be compliant with the current (1.1b) \
  and upcoming (1.2) TSS specifications available from the Trusted Computing \
  Group website: http://www.trustedcomputinggroup.org. \
  "
SECTION = "tpm"
PR = "r0"
LICENSE = "CPL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3728dd9198d68b49f7f9ed1f3f50ba14"

SRC_URI += "http://sourceforge.net/projects/trousers/files/trousers/0.3.10/trousers-0.3.10.tar.gz"

SRC_URI[md5sum] = "27b7374d991874b4a0a973b1c952c79f"
SRC_URI[sha256sum] = "eb9569de5c66d9698f6c3303de03777b95ec72827f68b7744454bfa9227bc530"

inherit autotools useradd

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "tss"
USERADD_PARAM_${PN} = "-M -d /var/lib/tpm -s /bin/false -g tss tss"
