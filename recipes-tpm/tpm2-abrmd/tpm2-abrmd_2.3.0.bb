include ${BPN}.inc

SRC_URI[md5sum] = "1326e92638d4f55cc4553260e5c24b6c"
SRC_URI[sha256sum] = "63cb59be1fd21e6ae233c37a0aa4a59883a4885a7bfd2c7e69979c5048518d50"
SRC_URI += "https://github.com/tpm2-software/${BPN}/releases/download/${PV}/${BPN}-${PV}.tar.gz"
