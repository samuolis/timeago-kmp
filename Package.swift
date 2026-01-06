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
            url: "https://github.com/samuolis/timeago-kmp/releases/download/v0.1.10/TimeAgoKMP.xcframework.zip",
            checksum: "19ffc04dd4a34c9f6cba0e7e5d5f3dc6420bf4725c8d18b62779ced17b77b560"
        )
    ]
)
