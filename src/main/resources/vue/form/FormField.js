"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var FormField = /** @class */ (function () {
    // constructor();
    function FormField(_obj) {
        this.value = _obj && _obj.value || '';
        this.isValid = _obj && _obj.isValid || null;
        this.validationMsgs = _obj && _obj.validationMsgs || [];
    }
    return FormField;
}());
exports.default = FormField;
