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
            url: "https://github.com/samuolis/timeago-kmp/releases/download/v0.1.5/TimeAgoKMP.xcframework.zip",
            checksum: "1cb4f0a0314ad6f46796b67f344f95d04cc96a536c1b77514d288a9bcfc45abc"
        )
    ]
)
