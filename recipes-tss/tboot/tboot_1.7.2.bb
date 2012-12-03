SUMMARY = ""
DESCRIPTION = " \
  "
SECTION = "bootloaders"
PR = "r0"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://COPYING;md5=661cd71df9c2b05204f9fef109bb7ff5"
DEPENDS = "libtspi openssl zlib"


SRC_URI += " \
    http://downloads.sourceforge.net/project/tboot/tboot/tboot-1.7.2.tar.gz \
    file://tboot-cross-compile.patch \
"

SRC_URI[md5sum] = "c8f981e5ee0a36a033e96383835c603b"
SRC_URI[sha256sum] = "e0ed44d7ff7d2fa018fbf7cb9dccfaee47269d9a9f4a4688824aaa32353ab323"

COMPATIBLE_HOST = '(x86_64|i.86).*-(linux|freebsd.*)'

do_compile() {
    oe_runmake SUBDIRS:=tboot CPP="${HOST_PREFIX}cpp ${TOOLCHAIN_OPTIONS}" LDFLAGS="" CFLAGS=""
    oe_runmake -C utils CFLAGS+="-std=gnu99"
}
