inherit setuptools3
require python-pyelftools.inc

RDEPENDS_${PN} = "python3 python3-contextlib python3-debugger python3-pprint"
RDEPENDS_${PN}_class-native = "python3"

