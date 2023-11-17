# SGU x Commsult Calculator Demo
![Application Preview](https://i.ibb.co/xSszP6f/preview.jpg)

This project is the final project of a collaboration/workshop held between SGU and Commsult. The project's goal is to properly implement a calculator which must pass the tests provided, found in:
`src/test/java/calculator/CalculatorImplTest.java`

The current version of the calculator is fully functional for all basic and additional operators and values. If you find any bugs or want to recommend a feature, please feel free to create an issue and we'll respond as soon as possible/convenient.

## Operator List
| Operators | Example in Formula | Example Output |
|--|--|--|
| Addition [ + ] | `0 + 1` | `1` |
| Subtraction [ - ] | `3 - 1` | `2` |
| Multiplication [ * ] | `1 * 3` | `3` |
| Division [ / ] | `8 / 2` | `4` |
| Parentheses [ () ] | `8 - (6 / 2)` | `5` |
| Exponents [ ^ ] | `2 + 2^2` | `6` |
| Square Root [ s ] | `s49` | `7` |
| Backspace [ 🔙 ] | `N/A` | `N/A` |
| Clear Entry [ CE ] | `N/A` | `N/A` |
| All Clear [ AC ] | `N/A` | `N/A` |

<sup>Note: This calculator takes precedence based on the **BODMAS** rules, **NOT PEMDAS** . Divisions take precedence over Multiplication in this implementation, so results may vary. We are aware that technically, these systems are the same order of operations, but programmatically our implementation takes division with precedence.</sup>

## **Examples of valid, parse-able inputs:**
This calculator was written in and handles doubles as the primary number format (in simple terms, decimals *are* supported). The calculator also handles inconsistent formatting of whitespaces so readability takes precedence for the user.

| Formula Input | Significance
|--|--|
| `16 + 4/2` | Mixed Order + Inconsistent Spacing |
| `(3 + (1 + (1 + 1 / 2) + (2 * 1 / 4 + 1))) * ( 10 / 5 + (1 / 4 + 3 / 4 ) )` | Long, Mixed Order, Inconsistent Spacing, Parentheses |
| `10--10` | Double Operator, Negative Numbers |
| `sqrt(25)` or `s25` | Square Root Operator, Brackets Included |

## Credits & Appreciation
| Project Developers | Community Sources |
|--|--|
| Dylan Tirta Kurniawan | [Open JavaFX Community](https://openjfx.io/) |
| Hendika Indrawinata Lukman | [FXDocs Team & Contributors](https://fxdocs.github.io/docs/html5/) |
|  |  |
<sup>*in collaboration with Commsult AG*</sup>

Many thanks and sincerest appreciation to those in the JavaFX community for their countless contributions and documentation efforts, without whom this project would be significantly more stressful and less enjoyable.