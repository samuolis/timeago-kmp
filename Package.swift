// swift-tools-version:5.9
import PackageDescription

let package = Package(
    name: "TimeAgoKMP",
    platforms: [
        .iOS(.v14),
        .macOS(.v12)
    ],
    products: [
        .library(
            name: "TimeAgoKMP",
            targets: ["TimeAgoKMP"]
        )
    ],
    targets: [
        .binaryTarget(
            name: "TimeAgoKMP",
            url: "https://github.com/samuolis/timeago-kmp/releases/download/v0.1.3/TimeAgoKMP.xcframework.zip",
            checksum: "2f3ac066a7ee444d8a989e4cef63b4a416c242ebcccb67e499d28c8da5ac4a27"
        )
    ]
)
