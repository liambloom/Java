// jshint esversion:6
const fs = require("fs");
const rl = require("readline-sync");
const getValueOfType = (type, prompt) => {
  let value = rl.question(prompt || type);
  switch (type) {
    case "String":
      return `"${value.replace(/(?=[\\"'])/g, "\\")}"`;
    case "byte":
    case "short":
    case "int":
    case "long":
      value = parseInt(value);
      return isNaN(value) ? getValueOfType(type, prompt) : value.toString();
    case "float":
    case "double":
      value = parseFloat(value);
      return isNaN(value) ? getValueOfType(type, prompt) : value.toString();
    case "char":
      return /^[\\"']$/.test(value) ? `'\\${value}'` : /^\\?.$/.test(value) ? `'${value}'` : getValueOfType(type, prompt);
    case "boolean":
      return /^(?:true|false)$/.test(value) ? value : getValueOfType(type, prompt);
    default:
      throw new Error(`Type ${type} not supported`);
  }
};
class Java extends String {
  constructor(java) {
    super(java);
    this.includesNonCode = true;
  }
  removeNonCode() {
    if (!this.includesNonCode) return this;
    const nonCodeRegexGlobal = /(?<=^.*)(?:\/\/.*$|\/\*[^]*?\*\/)|".*?(?<=(?<!\\)(?:\\{2})*)"/gm;
    //const nonCodeRegex = new RegExp(nonCodeRegexGlobal.source, "m");
    let current;
    let removed = [];
    let java = this;
    while ((current = nonCodeRegexGlobal.exec(java)) !== null) {
      console.log(current);
      removed.push({
        string: current[0],
        index: current.index,
        length: current[0].length
      });
      java = new Java(java.slice(0, current.index) + java.slice(current.index + current[0].length));
    }
    java.removed = removed;
    java.classesBefore = this.classesBefore;
    java.classesAfter = this.classesAfter;
    java.includesNonCode = false;
    //console.log(java.includesNonCode);
    return java;
  }
  restoreNonCode() {
    if (this.includesNonCode) return this;
    let java = this;
    if (!this.includesNonCode) {
      for (let removed of this.removed) {
        java = new Java(java.slice(0, removed.index - 1) + removed.string + java.slice(removed.index + removed.length));
      }
    }
    java.classesBefore = this.classesBefore;
    java.classesAfter = this.classesAfter;
    java.includesNonCode = true;
    return java;
  }
  publicClass() { // returns "pu", rest of class ends up in classesAfter
    //console.log(this);
    let java = this.removeNonCode();
    let start = java.indexOf("public class");
    let end = java.getMatchingParenthesis(java.substr(start).indexOf("{"));
    if (!this.includesNonCode) {
      for (let removed of java.removed) {
        if (removed.index <= start) start += removed.length;
        if (removed.index <= end) end += removed.length;
        else break;
      }
    }
    java = java.restoreNonCode();
    const classesBefore = new Java(java.slice(0, start - 1));
    const classesAfter = new Java(java.slice(end));
    java = new Java(java.slice(start, end));
    java.classesBefore = this.includesNonCode ? classesBefore : classesBefore.removeNonCode();
    java.classesAfter = this.includesNonCode ? classesAfter : classesAfter.removeNonCode();
    java = this.includesNonCode ? java : java.removeNonCode();
    java.removed = this.removed;
    java.classesBefore = this.includesNonCode;
    java.classesAfter = this.includesNonCode;
    //console.log(java.includesNonCode);
    return java;
  }
  allClasses() {
    let java = this;
    java = new Java(java.classesBefore.restoreNonCode() + java.restoreNonCode() + java.classesAfter.restoreNonCode());
    java = this.includesNonCode ? java : java.removeNonCode();
    java.classesBefore = this.classesBefore;
    java.classesAfter = this.classesAfter;
    return java;
  }
  getMatchingParenthesis(startIndex) {
    let java = this;
    java = java.removeNonCode();
    if (!this.includesNonCode) {
      for (let removed of this.removed) {
        if (removed.index <= startIndex) i -= removed.length;
        else break;
      }
    }
    let depth = 0;
    for (let i = startIndex; i < java.length; i++) {
      switch (java.charAt(i)) {
        case "{":
          depth++;
          break;
        case "}":
          depth--;
          break;
      }
      if (depth === 0) {
        if (!this.includesNonCode) {
          for (let removed of this.removed) {
            if (removed.index <= i) i += removed.length;
            else break;
          }
        }
        return i;
      }
    }
  }
  methods() {
    //console.log(this.removeNonCode().includesNonCode);
    console.log(this.removeNonCode().match(/(?<=^.*)(?:\/\/.*$|\/\*[^]*?\*\/)|".*?(?<=(?<!\\)(?:\\{2})*)"/gm));
    return this.removeNonCode()
      .publicClass()
      .match(/public\s+static\s+\w+(?:\[\])*\s+\w+\s*\([^\)]*\)/gm)
      .map(e => { // Turns it into an object for easy accessability
        const split = e.split(/\s/g);
        return {
          header: e,
          returnValue: split[2] !== "void",
          name: split[3],
          namePretty: split[3].split(/(?=[A-Z]|(?<!\d)\d|(?<=\d)[A-z])/g).join(" ").replace(/^/, $1 => $1.toUpperCase()),
          args: e.match(/(?<=\().*?(?=\))/)[0] // Get arguments
            .match(/\w+\s+\w+(?=\s*,?)/g) // Get each argument separate
            .map((e, i, a) => { // get type and name of each argument
              const arg = e.split(/\s+/);
              return {
                both: `${arg[0]} ${arg[1]}`,
                type: arg[0],
                name: arg[1],
                isLast: i === a.length - 1
              };
            })
        };
      });
    /**
     * methodHeaders
     * @property {string}   returnType  - The type returned by the method
     * @property {string}   name        - The name of the method
     * @property {string}   namePretty  - The name of the method in a human readable format (spaces and capitalization added)
     * @property {array}    args        - The data for each argument
     * @property {string}   args.type   - The type of an argument
     * @property {string}   args.name   - The name of an argument
     * @property {string}   args.both   - A string with the type followed by the name
     * @property {boolean}  args.isLast - Is this the last argument?
     */
  }
  addMethod(method) {
    let java = this.allClasses().restoreNonCode();
    const end = java.getMatchingParenthesis(java.substr(java.indexOf("public static")).indexOf("{"));
    return new Java(java.slice(0, end - 1) + method + java.slice(end));
  }
}
void function main() {
  let newMethod = "";
  const className = rl.question("What Class?");
  const fileName = `./${className}.java`;
  if (fs.existsSync(fileName)) {
    const methods = new Java(fs.readFileSync(fileName, "utf-8")).methods();
    const usePreset = rl.keyInSelect(["Ask at runtime", "Use preset values"], "Where to get arguments?");
    let newMethod = `  public static void runAll${className} () {\n`;
    for (let method of methods) {
      if (usePreset) console.log(method.header);
      newMethod += `    System.out.println("${method.namePretty}:\\n");\n`;
      if (method.returnValue) newMethod += "    System.out.println(";
      newMethod += method.name + "(";
      for (let arg of method.args) {
        let value;
        if (usePreset) value = rl.question();
        if (!usePreset || value.toLowerCase() === "ask") {
          newMethod += `Ask.for${arg.type}(${arg.both})`;
        }
        else {
          newMethod += getValueOfType(arg.type, arg.both);
        }
        if (!arg.isLast) newMethod += ", ";
      }
      newMethod += ")";
      if (method.returnValue) newMethod += ")";
      newMethod += ";\n    System.out.println();\n";
    }
    newMethod += "  }\n";
    fs.writeFileSync(new Java(fs.readFileSync("./Main.java")).addMethod(newMethod));
    console.log("Done!");
  }
  else {
    console.log("Class does not exist");
    main();
  }
}();